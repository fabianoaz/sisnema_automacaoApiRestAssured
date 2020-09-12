package tests;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

//import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class OpenWeatherTest
{
	@Test
	public void validaAcessoSemToken_NaoAutorizado_401() 
	{
		given()
			.param("q","Porto Alegre,br")
			.param("appid","")
			.param("units","metric")
			.param("lang","pt_br")
			.log().all()
		.when()
			.get("http://api.openweathermap.org/data/2.5/weather")
		.then()
			.statusCode(401)
			.log().all()
		;
	}

	@Test
	public void validaAcessoComToken_Autorizado_200() 
	{
		given()
			.param("q","Porto Alegre,br")
			.param("appid","1746eca6dbefae1ba2682a600836b257")
			.param("units","metric")
			.param("lang","pt_br")
			.log().all()
		.when()
			.get("http://api.openweathermap.org/data/2.5/weather")
		.then()
			.statusCode(200)
			.log().all()
		;
	}
	
	@Test
	public void validaDadosRetornados() 
	{
		given()
			.param("q","Porto Alegre,br")
			.param("appid","1746eca6dbefae1ba2682a600836b257")
			.param("units","metric")
			.param("lang","pt_br")
			.log().all()
		.when()
			.get("http://api.openweathermap.org/data/2.5/weather")
		.then()
			.log().all()
			.statusCode(200)
			.body("name", Matchers.is("Porto Alegre"))
			.body("sys.country", Matchers.is("BR"))
		;
	}
}
