package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        url '/appointment/medicine'
        body(
                recordId:"317498db-9841-408a-b01d-6f7d4d8da4fc",
                medicines:"Aspirin"
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
    }
}