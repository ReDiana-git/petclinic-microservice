package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/appointment/createAppointments'
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 500
        headers {
            contentType(applicationJson())
        }
    }
}
