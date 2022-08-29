import React from 'react'
import { useSearchParams } from 'react-router-dom'

export default function Details() {
    let state = {
        detailData: [
            { id: '001', context: 'Hello' },
            { id: '002', context: 'Word' },
            { id: '003', context: 'React' },
        ]
    }
    // const params = useParams()
    // const param = state.detailData.find((detail) => {
    //     return params.id === detail.id
    // })

    let [searchParams] = useSearchParams()
    const param = state.detailData.find((detail) => {
        return searchParams.get('id') === detail.id
    })
    return (
        <div>
            <ul>
                <li>{param.context}</li>
            </ul>
        </div>
    )

}
