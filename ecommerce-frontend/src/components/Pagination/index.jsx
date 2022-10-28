import React from 'react';
import PropTypes from 'prop-types'

Pagination.propTypes = {
    totalPage: PropTypes.number,
    currentPage: PropTypes.number,
    handleFilterPageChange: PropTypes.func
}

Pagination.defaultProps = {
    handleFilterPageChange: null
}

function Pagination(props) {
    const { currentPage, totalPage, handleFilterPageChange } = props;
    const displayPages = [];
    for (let i = 1; i <= totalPage; i++) {
        displayPages.push(i);
    }

    const handlePageChange = (newPage) => {
        if (handleFilterPageChange) {
            handleFilterPageChange(newPage)
        }
    }

    return (
        <div class="row mt-5">
            <div class="col text-center">
                <div class="block-27">
                    <ul>
                        <li><a onClick={()=>handlePageChange(currentPage-1)} href="#" className={currentPage <= 0 ? "disabled" : ""}>&lt;</a></li>

                        {displayPages.map((number, index) =>
                            <li className={number === currentPage+1 ? "active" : ""} key={index}><a onClick={()=>handlePageChange(number-1)} href="#" >{number}</a></li>
                        )}

                        <li><a onClick={()=>handlePageChange(currentPage+1)}  href="#"  className={currentPage >= totalPage-1 ? "disabled" : ""}>&gt;</a></li>
                    </ul>
                </div>
            </div>
        </div>
    )
}


export default Pagination;

