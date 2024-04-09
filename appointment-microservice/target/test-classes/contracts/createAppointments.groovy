package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method POST()
        url '/appointment/createAppointments'
        body(
                ownerId:"1",
                petId:"2",
                vetId:"1",
                appointmentDate: "2024-03-05T21:00:00"
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 201
    }
}
