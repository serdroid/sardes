package info.serdroid.sardes.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.impl.gradle.Gradle;
import org.junit.Test;
import org.junit.runner.RunWith;

import info.serdroid.sardes.model.UserInfo;

@RunWith(Arquillian.class)
public class UserInfoEndpointTest {

	@ArquillianResource
	private URL contextPath;

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap.create(WebArchive.class);
		Archive<?>[] archives = Gradle.resolver().forProjectDirectory(".")
				.importCompileAndRuntime()
				.resolve()
				.asList(JavaArchive.class).toArray(new Archive[0]);
		war.addPackages(true, "info.serdroid.sardes")
				.addAsWebInfResource("beans-test.xml", "beans.xml")
				.addAsLibraries(archives);
		System.out.println(war.toString(true));
		return war;
	}
	
	@Test
	public void testAuthorize() {
		Client client = ClientBuilder.newClient();
		Response response = client.target(contextPath + "rest/user/123").request().get();
		assertThat(response.getStatus()).isEqualTo(200);
		UserInfo userInfo = response.readEntity(UserInfo.class);
		assertThat(userInfo.getUserid()).isEqualTo("123");
	}

}
