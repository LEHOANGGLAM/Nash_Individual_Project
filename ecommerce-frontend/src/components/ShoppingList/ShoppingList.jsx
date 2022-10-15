import React, { Component } from 'react';
import HeadWrap from './HeadWrap';

class ShoppingList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            fields: {},
            errors: {},
            successful: false,
            message: "",
        }
    }

    componentDidMount() {

    }

    render() {
        return (
            <>
                <HeadWrap />
            </>
        );
    }
}

export default ShoppingList;