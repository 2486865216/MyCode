import React, { Component } from 'react'
import { Button,Input } from 'antd'
import { Space } from 'antd';
import {
    HomeOutlined,
    SettingFilled,
    SmileOutlined,
    SyncOutlined,
    LoadingOutlined,
} from '@ant-design/icons';

export default class App extends Component {
    render() {
        return (
            <div>
                <Button type="primary">Primary Button</Button>
                <Input/>
                <Space>
                    <HomeOutlined />
                    <SettingFilled />
                    <SmileOutlined />
                    <SyncOutlined spin />
                    <SmileOutlined rotate={180} />
                    <LoadingOutlined />
                </Space>,
            </div>
        )
    }
}