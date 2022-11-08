import React from 'react';
import { useState } from 'react';
import { styles } from './styles'

function ButtonOpenChat(props) {
    const [hovered, setHovered] = useState(false)

    return (
        <div style={{ position: 'fixed', bottom: 24, right: 24 }}>
            <div
                className='transition-5'
                style={{
                    ...styles.avatarHello,
                    ...{ opacity: hovered ? '1' : '0'}
                }}
            >Hey, you need support?</div>

            <div style={{
                ...styles.chatWithMeButton,
                ...{ border: hovered ? '1px solid #f9f0ff' : '4px solid' }
            }}
                onClick={()=>props.onClick && props.onClick()}
                onMouseEnter={() => setHovered(true)}
                onMouseLeave={() => setHovered(false)}>

            </div>
        </div>
    );
}

export default ButtonOpenChat;