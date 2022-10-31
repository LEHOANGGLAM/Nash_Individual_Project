import React from 'react';
import './TextEditor.scss'
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import parse from 'html-react-parser'
import PropTypes from 'prop-types'

TextEditor.propTypes = {
    text: PropTypes.string,
    setText: PropTypes.func,
}

TextEditor.defaultProps = {
    text: null,
    setText: null
}

function TextEditor(props) {
    const { text, setText } = props;

    const handleTextChange = (newText) => {
        if (setText) {
            setText(newText)
        }
    }

    return (
        <div >
            <CKEditor
                editor={ClassicEditor}
                data={text}
                
                onChange={(event, editor) => {
                    const data = editor.getData();
                    handleTextChange(data)
                }}

            />
        </div>
    );


}

export default TextEditor;
