import React from 'react';
import PropTypes from 'prop-types'

Pagination.propTypes = {
    totalPage: PropTypes.number,
}

Pagination.defaultProps = {
    totalPage: null,
}

function Pagination(props) {
    const { totalPage } = props;

    return (
        <div class="row mt-5">
            <div class="col text-center">
                <div class="block-27">
                    <ul >
                        {/* <li ><a href={this.handlePageChange(2)}>&lt;</a></li>
            {paging}
            <li><a href={this.handlePageChange(3)}>&gt;</a></li> */}
                    </ul>
                </div>
            </div>
        </div>
    )
}


export default Pagination;

