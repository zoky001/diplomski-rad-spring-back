package hr.foi.diplomski.rad

import com.github.tomakehurst.wiremock.WireMockServer
import hr.foi.diplomski.rad.client.mock.mappings.ClientMockMapping
import hr.foi.diplomski.rad.collateral.mock.mappings.CollateralMockMapping
import hr.foi.diplomski.rad.core.mock.mappings.RispoMockMapping
import hr.foi.diplomski.rad.exposure.mock.mappings.ExposureMockMapping
import hr.foi.diplomski.rad.group.mock.mappings.GroupMockMapping
import hr.foi.diplomski.rad.interest.rate.reference.mock.mappings.InterestRateReferenceMockMapping
import hr.foi.diplomski.rad.povos.mock.mappings.PovosMockMapping
import hr.foi.diplomski.rad.sova.client.mock.mappings.SovaMockMapping
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig

@SpringBootApplication
class RadApplication {

    @Value('${mySettings.mockServices.enabled}')
    private static boolean enabledMock

    static void main(String[] args) {


        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(10101).usingFilesUnderClasspath("src/main/resources"))
        wireMockServer.start()


        ClientMockMapping.init()
        RispoMockMapping.init()
        CollateralMockMapping.init()
        ExposureMockMapping.init()
        InterestRateReferenceMockMapping.init()
        GroupMockMapping.init()
        PovosMockMapping.init()
        SovaMockMapping.init()

        SpringApplication.run(RadApplication, args)

    }

}
