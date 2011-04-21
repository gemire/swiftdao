package infrastructure;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.swiftdao.demo.MockSingleKeyEntity;

/**
 * 所有测试DAO的单元测试类。
 * 
 * @author Wang Yuxing
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/hsqldb_ds.xml", "classpath:spring/mock_dao.xml" })
public abstract class BaseDaoUnitTest extends BaseUnitTest {

	protected List<String> springConfigLocations = new ArrayList<String>();
	protected static ApplicationContext appContext = null;

	public BaseDaoUnitTest() {
		logger = LoggerFactory.getLogger(this.getClass());
		// this.initConfigLocations();
		String[] configPaths = springConfigLocations.toArray(new String[springConfigLocations.size()]);
		if (appContext == null) {
			appContext = new ClassPathXmlApplicationContext(configPaths);
		}
	}

	protected MockSingleKeyEntity createDefaultPojo(Long id) {
		MockSingleKeyEntity entity = new MockSingleKeyEntity();
		id = generateLongEntityID();
		System.out.println("ID 1: " + id);
		entity.setId(id);
		entity.setKey("key");
		entity.setStrValue("value");
		return entity;
	}

	protected List<MockSingleKeyEntity> createKeyedPojos(int count) {
		List<MockSingleKeyEntity> list = new ArrayList<MockSingleKeyEntity>();
		for (int i = 0; i < count; i++) {
			MockSingleKeyEntity pojo = new MockSingleKeyEntity(Calendar.getInstance().getTimeInMillis() + "" + i);
			pojo.setStrValue("" + i % 2);
			pojo.setIntValue(i % 2);
			pojo.setCreationTime(Calendar.getInstance());
			list.add(pojo);
		}
		return list;
	}

	/**
	 * Generate entity ID
	 * 
	 * @return
	 */
	protected long generateLongEntityID() {
		return Calendar.getInstance().getTimeInMillis();
	}
}