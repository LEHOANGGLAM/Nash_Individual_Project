import React, { useRef, useEffect, useState } from 'react';
import ButtonOpenChat from './ButtonOpenChat';
import ChatWindow from './ChatWindow';

function SupportEngine(props) {
    const ref = useRef(null)
    const [visible, setVisible] = useState(false)

    useEffect(() => {
        function handleClickOutside(e) {
            if (ref.current && !ref.current.contains(e.target)) {
                setVisible(false)
            }
        }
        document.addEventListener("mousedown", handleClickOutside)
        return () => {
            document.removeEventListener("mousedown", handleClickOutside)
        }
    }, [ref])

    return (
        <div ref={ref}>
            <ChatWindow visible={visible} />
            <ButtonOpenChat onClick={() => setVisible(true)} />
        </div>
    );
}

export default SupportEngine;



// createUserWithEmailAndPassword(auth, this.state.fields["email"], this.state.fields["password"])
// .then((userCredential) => {
//     // Signed in
//     const user = userCredential.user;
//     console.log(user);
// })
// .catch((error) => {
//     const errorCode = error.code;
//     const errorMessage = error.message;
//     // ..
// });