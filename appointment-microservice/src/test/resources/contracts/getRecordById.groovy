package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url('/appointment/medicalRecord/317498db-9841-408a-b01d-6f7d4d8da4fc')
        headers {
//            contentType(applicationJson())
        }
    }
    response {
        status 200
        body(
            id: "317498db-9841-408a-b01d-6f7d4d8da4fc" ,
            ownerId: 1 ,
            vetId: 1 ,
            state: "init" ,
            petId: 2 ,
            appointmentId: "2056e53e-72b7-4110-9afb-b219d2907dec" ,
            consultationId: "70c3ad0c-547e-458f-901b-76c1a9dce9be" ,
            paymentId: "2536026b-4b7b-4d1b-86c3-72214e952a23" ,
            medicineId: "c3f865b1-4b3c-4c0a-9bc2-7c703f2ba99e" ,

        )
        headers {
            contentType(applicationJson())  // 确保响应类型为 JSON
        }
    }
}