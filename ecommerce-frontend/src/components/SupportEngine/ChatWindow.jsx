import React from 'react';
import { useState } from 'react';
import { styles } from './styles'

function ChatWindow(props) {
    return (
        <div style={{
            ...styles.supportWindow,
            ...{opacity: props.visible ? '1' : '0'}
        }}
        >
            
        </div>
    );
}

export default ChatWindow;