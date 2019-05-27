#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

import java.util.function.Supplier;

import org.apache.ignite.IgniteCache;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import fi.xaxis.a2.ignite.utils.IgniteBasicService;

@Component
public class ${entityName}ServiceImpl extends IgniteBasicService<${entityName}> implements ${entityName}Service {

	@Reference(target = "(cache.name=" + ${entityName}CacheSupplier.CACHE_NAME + ")")
	public void setCacheSupplier(Supplier<IgniteCache<String, ${entityName}>> cacheSupplier) {
		this.cacheSupplier = cacheSupplier;
	}

	@Activate
	@Override
	public void activate() {
		super.activate();
	}

}
