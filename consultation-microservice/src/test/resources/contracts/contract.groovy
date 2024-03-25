package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        url "/appointment/createConsultation"
        body("""
        {
            "consultationId": "12345"
        }
        """)
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status CREATED()
    }
}