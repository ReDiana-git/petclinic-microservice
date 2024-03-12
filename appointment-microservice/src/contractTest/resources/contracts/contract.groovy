package contracts

Contract.make {
    request {
        method 'POST'
        url '/appointment/createAppointments'
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 201
        headers {
            contentType(applicationJson())
        }
    }
}
