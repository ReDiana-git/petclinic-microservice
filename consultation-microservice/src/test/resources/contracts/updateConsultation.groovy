package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        url "/appointment/updateConsultation"
        body(
                recordId:"317498db-9841-408a-b01d-6f7d4d8da4fc",
                medicines:"Aspirin",
                symptom:"cough"
        )
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status 200
    }
}