package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        url "/appointment/deleteConsultation"
        headers {
//            contentType(applicationJson())
        }
    }

    response {
        status 200
    }
}