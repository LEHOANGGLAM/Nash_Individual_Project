import React from 'react';

function Overview(props) {



    return (
        <div>
            <div class="MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-3 css-vemis6">
                <div class="MuiGrid-root MuiGrid-item css-1wxaqej">
                    <h4 class="MuiTypography-root MuiTypography-h4 css-1xwzh4e">Analytics</h4>
                </div>
                <div class="MuiGrid-root MuiGrid-item css-1a48ivd">
                    <button class="MuiButtonBase-root MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeMedium MuiButton-outlinedSizeMedium MuiButton-disableElevation css-1lue454" tabindex="0" type="button">
                        <span class="MuiButton-startIcon MuiButton-iconSizeMedium css-1l6c7y9">
                            <svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeSmall css-1k33q06" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="ReportsIcon">
                                <svg viewBox="0 0 20 20" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M6 2C5.46957 2 4.96086 2.21071 4.58579 2.58579C4.21071 2.96086 4 3.46957 4 4V16C4 16.5304 4.21071 17.0391 4.58579 17.4142C4.96086 17.7893 5.46957 18 6 18H14C14.5304 18 15.0391 17.7893 15.4142 17.4142C15.7893 17.0391 16 16.5304 16 16V7.414C15.9999 6.88361 15.7891 6.37499 15.414 6L12 2.586C11.625 2.2109 11.1164 2.00011 10.586 2H6ZM8 12C8 11.7348 7.89464 11.4804 7.70711 11.2929C7.51957 11.1054 7.26522 11 7 11C6.73478 11 6.48043 11.1054 6.29289 11.2929C6.10536 11.4804 6 11.7348 6 12V15C6 15.2652 6.10536 15.5196 6.29289 15.7071C6.48043 15.8946 6.73478 16 7 16C7.26522 16 7.51957 15.8946 7.70711 15.7071C7.89464 15.5196 8 15.2652 8 15V12ZM10 9C10.2652 9 10.5196 9.10536 10.7071 9.29289C10.8946 9.48043 11 9.73478 11 10V15C11 15.2652 10.8946 15.5196 10.7071 15.7071C10.5196 15.8946 10.2652 16 10 16C9.73478 16 9.48043 15.8946 9.29289 15.7071C9.10536 15.5196 9 15.2652 9 15V10C9 9.73478 9.10536 9.48043 9.29289 9.29289C9.48043 9.10536 9.73478 9 10 9ZM14 8C14 7.73478 13.8946 7.48043 13.7071 7.29289C13.5196 7.10536 13.2652 7 13 7C12.7348 7 12.4804 7.10536 12.2929 7.29289C12.1054 7.48043 12 7.73478 12 8V15C12 15.2652 12.1054 15.5196 12.2929 15.7071C12.4804 15.8946 12.7348 16 13 16C13.2652 16 13.5196 15.8946 13.7071 15.7071C13.8946 15.5196 14 15.2652 14 15V8Z"></path>
                                </svg>
                            </svg>
                        </span>Reports </button>
                    <div class="MuiFormControl-root MuiTextField-root css-26emry">
                        <label class="MuiFormLabel-root MuiInputLabel-root MuiInputLabel-formControl MuiInputLabel-animated MuiInputLabel-shrink MuiInputLabel-sizeSmall MuiInputLabel-outlined MuiFormLabel-colorPrimary MuiFormLabel-filled css-1x3fk9d" data-shrink="true" for=":rj:" id=":rj:-label">Period</label>
                        <div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl MuiInputBase-sizeSmall css-52mkzu">
                            <div tabindex="0" role="button" aria-expanded="false" aria-haspopup="listbox" aria-labelledby=":rj:-label :rj:" id=":rj:" class="MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o81jgk">Last week</div>
                            <input aria-hidden="true" tabindex="-1" class="MuiSelect-nativeInput css-1k3x8v3" value="week"/>
                                <svg class="MuiSvgIcon-root MuiSvgIcon-fontSizeMedium MuiSelect-icon MuiSelect-iconOutlined css-1cdths" focusable="false" aria-hidden="true" viewBox="0 0 24 24" data-testid="ArrowDropDownIcon">
                                    <path d="M7 10l5 5 5-5z"></path>
                                </svg>
                                <fieldset aria-hidden="true" class="MuiOutlinedInput-notchedOutline css-17cylm7">
                                    <legend class="css-1in441m">
                                        <span>Period</span>
                                    </legend>
                                </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Overview;