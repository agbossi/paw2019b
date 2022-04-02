import React, {Component, useState} from "react";

import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import ApiCalls from "../../api/apiCalls"
import "./Login.css"
import './AdminHome.css';
import '../CardContainer.css'
import '../../i18n/i18n'

import {useLocation, useNavigate} from "react-router-dom";
import {useTranslation} from "react-i18next";


const required = (value) => {
    if (!value) {
        return (
            <div className="alert alert-danger" role="alert">
                This field is required!
            </div>
        );
    }
};

const WrappedLogin = props => {
    const navigate = useNavigate()
    const location = useLocation()
    const {t} = useTranslation()
    console.log('navigate info')
    console.log(navigate)
    console.log(navigate.state)
    console.log('location info')
    console.log(location)
    console.log(location.search !== null && location.search !== undefined)
    return <Login navigate={navigate} t={t} location={location} {...props} />
}

class Login extends Component {

    constructor(props) {
        super(props);
        this.handleLogin = this.handleLogin.bind(this);
        this.onChangeEmail = this.onChangeEmail.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
        //this.loginRedirect = this.loginRedirect.bind(this);
        let message = '';
        console.log('path en constructor: ' + localStorage.getItem("path"))
        if(props.location.search !== null && props.location.search !== undefined) {
            message = this.props.t('errors.auth')
        }
        this.state = {
            email: "",
            password: "",
            loading: false,
            message: message,
            redirect: localStorage.getItem("path")
        };

    }

    onChangeEmail(e) {
        this.setState({
            email: e.target.value
        });
    }

    onChangePassword(e) {
        this.setState({
            password: e.target.value
        });
    }

    handleLogin(e) {
        e.preventDefault();

        this.setState({
            message: "",
            loading: true
        });

        this.form.validateAll();
        let redirectPath;
        if (this.checkBtn.context._errors.length === 0) {
            ApiCalls.login(this.state.email, this.state.password).then(
                (resp) => {
                    if (resp.status === 200) {
                        switch (localStorage.getItem('role')) {
                            case "ROLE_ADMIN":
                                redirectPath = "/paw-2019b-4/admin"
                                break;
                            case "ROLE_DOCTOR":
                                redirectPath = "/paw-2019b-4/doctor"
                                break;
                            case "ROLE_USER":
                                redirectPath = "/paw-2019b-4"
                                break;
                        }
                        if (localStorage.getItem("path") !== null) {
                            console.log('entro en memoria')
                            console.log('/paw-2019b-4' + localStorage.getItem("path"))
                            this.props.navigate('/paw-2019b-4' + localStorage.getItem("path"));
                        } else if(redirectPath !== null && redirectPath !== undefined) {
                            console.log('entro en redirect principal')
                            this.props.navigate(redirectPath);
                        } else {
                            console.log('pasaron cosas')
                            this.props.navigate('/paw-2019b-4/login')
                        }
                        localStorage.removeItem("path")
                        window.location.reload()

                    } else if (resp.status === 401) {
                        this.setState({
                            loading: false,
                            message: "Email or password are not correct. Try again"
                        });
                        //this.props.navigate('/paw-2019b-4/login')
                        //window.location.reload()
                    }
                },
                error => {
                    const resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();

                    this.setState({
                        loading: false,
                        message: resMessage
                    });
                }
            );
        } else {
            this.setState({
                loading: false
            });
        }
    }

    render() {
        return (
            <div className="col-md-12">
                <div className="card card-container centered background">
                    <Form
                        onSubmit={this.handleLogin}
                        ref={c => {
                            this.form = c;
                        }}
                    >
                        <div className="form-group" className="labels mb-4">
                            <label htmlFor="email" >{this.props.t("FORM.email")}</label>
                            <Input
                                type="email"
                                className="form-contro text-fields"
                                name="email"
                                value={this.state.email}
                                onChange={this.onChangeEmail}
                                validations={[required]}
                            />
                        </div>

                        <div className="form-group" className="labels">
                            <label htmlFor="password">{this.props.t("FORM.password")}</label>
                            <Input
                                type="password"
                                className="form-control text-fields"
                                name="password"
                                value={this.state.password}
                                onChange={this.onChangePassword}
                                validations={[required]}
                            />
                        </div>

                        <div className="form-group">
                            <button
                                className="btn btn-block login-button"
                                disabled={this.state.loading}
                            >
                                {this.state.loading && (
                                    <span className="spinner-border spinner-border-sm"/>
                                )}
                                <span>{this.props.t("NAVBAR.login")}</span>
                            </button>
                        </div>

                        {this.state.message && (
                            <div className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    {this.state.message}
                                </div>
                            </div>
                        )}
                        <CheckButton
                            style={{ display: "none" }}
                            ref={c => {
                                this.checkBtn = c;
                            }}
                        />
                    </Form>
                </div>
            </div>
        );
    }

}

export default WrappedLogin