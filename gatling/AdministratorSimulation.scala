
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class AdministratorSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://192.168.99.100:8082")
		.proxy(Proxy("192.168.99.100", 8082).httpsPort(8082))
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.9")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map(
		"Accept" -> "text/css,*/*;q=0.1",
		"Proxy-Connection" -> "keep-alive")

	val headers_8 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
		"Origin" -> "http://192.168.99.100:8082",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_10 = Map(
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"Origin" -> "http://192.168.99.100:8082",
		"Proxy-Connection" -> "keep-alive",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_15 = Map("Proxy-Connection" -> "keep-alive")

	val headers_22 = Map(
		"Origin" -> "http://192.168.99.100:8082",
		"Proxy-Connection" -> "keep-alive")

	val headers_23 = Map(
		"Accept" -> "text/event-stream",
		"Proxy-Connection" -> "keep-alive")

    val uri1 = "http://d3js.org"

	val scn = scenario("AdministratorSimulation")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/css/custom.css")
			.headers(headers_1),
            http("request_2")
			.get("/login")
			.headers(headers_0),
            http("request_3")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(6)
		.exec(http("request_4")
			.get("/admin/login")
			.headers(headers_0)
			.resources(http("request_5")
			.get("/admin/css/custom.css")
			.headers(headers_1),
            http("request_6")
			.get("/admin/login")
			.headers(headers_0),
            http("request_7")
			.get("/admin/css/custom.css")
			.headers(headers_1)))
		.pause(11)
		.exec(http("request_8")
			.post("/admin/login")
			.headers(headers_8)
			.formParam("email", "admin1@dashboard.com")
			.formParam("password", "123456")
			.resources(http("request_9")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(14)
		.exec(http("request_10")
			.post("/operator/admin")
			.headers(headers_10)
			.formParam("id", "map-operator3@dashboard.com")
			.resources(http("request_11")
			.post("/operator/admin")
			.headers(headers_10)
			.formParam("id", "chart-operator3@dashboard.com")))
		.pause(1)
		.exec(http("request_12")
			.post("/operator/admin")
			.headers(headers_10)
			.formParam("id", "incidents-operator3@dashboard.com"))
		.pause(9)
		.exec(http("request_13")
			.post("/operator/admin")
			.headers(headers_10)
			.formParam("id", "admin-operator3@dashboard.com"))
		.pause(6)
		.exec(http("request_14")
			.get("/dashboard/charts")
			.headers(headers_0)
			.resources(http("request_15")
			.get(uri1 + "/d3.v3.min.js")
			.headers(headers_15),
            http("request_16")
			.get("/css/c3.css")
			.headers(headers_1),
            http("request_17")
			.get(uri1 + "/login")
			.headers(headers_15),
            http("request_18")
			.get("/script/c3.min.js")
			.headers(headers_15),
            http("request_19")
			.get("/dashboard/info?t=1525947716673")
			.headers(headers_15)))
		.pause(1)
		.exec(http("request_20")
			.get("/dashboard/iframe.html")
			.headers(headers_0)
			.resources(http("request_21")
			.get("/dashboard/maps")
			.headers(headers_0),
            http("request_22")
			.post("/dashboard/287/2ncaj3pk/xhr_streaming?t=1525947717323")
			.headers(headers_22),
            http("request_23")
			.get("/dashboard/287/0kwwlzrm/eventsource")
			.headers(headers_23),
            http("request_24")
			.get("/script/map/map_sockets.js")
			.headers(headers_15),
            http("request_25")
			.get("/css/map.css")
			.headers(headers_1),
            http("request_26")
			.get("/script/map/map.js")
			.headers(headers_15),
            http("request_27")
			.get("/dashboard/info?t=1525947719421")
			.headers(headers_15)))
		.pause(1)
		.exec(http("request_28")
			.get("/incidents")
			.headers(headers_0)
			.resources(http("request_29")
			.get("/css/incidetails.css")
			.headers(headers_1),
            http("request_30")
			.get("/script/incident_view/incidents_update.js")
			.headers(headers_15),
            http("request_31")
			.get("/dashboard/info?t=1525947721111")
			.headers(headers_15)))
		.pause(1)
		.exec(http("request_32")
			.get("/admin/operators")
			.headers(headers_0)
			.resources(http("request_33")
			.post("/dashboard/457/0vpdib4d/xhr_streaming?t=1525947721776")
			.headers(headers_22),
            http("request_34")
			.get("/dashboard/457/4pnkutug/eventsource")
			.headers(headers_23),
            http("request_35")
			.get("/css/custom.css")
			.headers(headers_1)))
		.pause(9)
		.exec(http("request_36")
			.post("/operator/admin")
			.headers(headers_10)
			.formParam("id", "map-operator2@dashboard.com"))
		.pause(3)
		.exec(http("request_37")
			.get("/logout")
			.headers(headers_0)
			.resources(http("request_38")
			.get("/css/custom.css")
			.headers(headers_1)))

	setUp(scn.inject(rampUsers(200) over(60 seconds))).protocols(httpProtocol)
}