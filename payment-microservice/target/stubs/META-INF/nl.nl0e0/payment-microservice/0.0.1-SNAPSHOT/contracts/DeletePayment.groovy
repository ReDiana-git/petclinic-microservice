package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        url "/appointment/deletePayment"
        headers {
//            contentType(applicationJson())
        }
    }

    response {
        status 200
    }
}