package rdvmedecin.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = { "rdvmedecin.repository" })
@EnableAutoConfiguration
@ComponentScan(basePackages = { "rdv-medecins","rdvmedecin.metier" })
@EntityScan(basePackages={"rdvmedecin.entities" })
@EnableTransactionManagement
public class DomainAndPersistenceConfig {
	// la source de donnée MySQL
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/dbrdvmedecins");
		dataSource.setUsername("jpa");
		dataSource.setPassword("masterkey");
		return dataSource;
	}

	// le provider JPA - n'est pas nécessaire si on est satisfait des valeurs
	// par défaut utilisées par Spring boot
	// ici on le définit pour activer / désactiver les logs SQL
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}
	// l'EntityManageFactory et le TransactionManager sont définis avec des
	// valeurs par défaut par Spring boot
}
