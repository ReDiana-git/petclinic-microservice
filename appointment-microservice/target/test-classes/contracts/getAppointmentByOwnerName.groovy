package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        url '/appointment/getAppointments'
        body(
                firstName:"George",
                lastName:"Franklin"
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
    }
}