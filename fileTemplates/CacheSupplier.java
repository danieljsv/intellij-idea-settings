#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

import static org.osgi.service.component.annotations.ReferenceCardinality.OPTIONAL;

import java.util.function.Supplier;

import org.apache.ignite.Ignite;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import fi.xaxis.atm.cassandra.api.DataSource;
import fi.xaxis.atm.features.ignition.cassandra.AbstractCacheSupplier;
import fi.xaxis.atm.features.ignition.cassandra.CassandraStoreFactory;

@Component(service = { Supplier.class, ${entityName}CacheSupplier.class },
           property = "cache.name=" + ${entityName}CacheSupplier.CACHE_NAME,
           reference = {
	           @Reference(name = "cassandraStoreFactory",
	                      bind = "setCassandraStoreFactory",
	                      service = CassandraStoreFactory.class,
	                      cardinality = OPTIONAL),
	           @Reference(name = "ignite",
	                      bind = "setIgnite",
	                      service = Ignite.class),
	           @Reference(name = "dataSource",
	                      service = DataSource.class,
	                      cardinality = OPTIONAL)
           })
public class ${entityName}CacheSupplier extends AbstractCacheSupplier<String, ${entityName}> {

	#set( $regex = "([a-z])([A-Z]+)")
	#set( $replacement = "$1_$2")
	#set( $toSnake = $entityName.replaceAll($regex, $replacement).toLowerCase())
	public static final String CACHE_NAME = "${toSnake}";

	public ${entityName}CacheSupplier() {
		super(CACHE_NAME);
	}

	@Activate
	@Override
	public void postConstruct() {
		super.postConstruct();
	}

}
