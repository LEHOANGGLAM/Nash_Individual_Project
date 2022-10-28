import React, { Component } from 'react';
import Login from './Login';
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import 'react-tabs/style/react-tabs.css';
import Register from './Register';


class LoginAndRegister extends Component {

    render() {
        return (
            <section class="ftco-section">
                <div class="container ">
                    <div class="row justify-content-center " >
                        <div class="col-xl-10 ftco-animate fadeInUp ftco-animated " style={{ paddingLeft: '20%', paddingRight: '20%' }}>
                            <Tabs>
                                <TabList>
                                    <ul class="tabs-nav">
                                        <Tab><li><a>Login</a></li></Tab>
                                        <Tab><li><a>Register</a></li></Tab>
                                    </ul>
                                </TabList>
                                <div class="tabs-container"  >
                                    <TabPanel class="tab-content">
                                        <Login />
                                    </TabPanel>
                                    <TabPanel class="tab-content">
                                        <Register />
                                    </TabPanel>
                                </div>
                            </Tabs>
                        </div>
                    </div>
                </div>
            </section >
        );
    }
}

export default LoginAndRegister;