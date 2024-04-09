package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        url "/appointment/createMedicine"
        body("12345 ")
        headers {
//            contentType(applicationJson())
        }
    }

    response {
        status 201
    }
}