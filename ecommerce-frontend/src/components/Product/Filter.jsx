import React from 'react';
import PropTypes from 'prop-types'

Filter.propTypes = {
    filters: PropTypes.func,

}

Filter.defaultProps = {
    filters: null,
}

function Filter(props) {
    const { filters } = props;

    return (

        <div class="col-md-4 col-lg-2">
            <div class="sidebar">
                <div class="sidebar-box-2">
                    <h2 class="heading">Categories</h2>
                    <div class="fancy-collapse-panel">
                        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingOne">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Men's Shoes
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                    <div class="panel-body">
                                        <ul>
                                            <li><a href="/">Sport</a></li>
                                            <li><a href="/">Casual</a></li>
                                            <li><a href="/">Running</a></li>
                                            <li><a href="/">Jordan</a></li>
                                            <li><a href="/">Soccer</a></li>
                                            <li><a href="/">Football</a></li>
                                            <li><a href="/">Lifestyle</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="sidebar-box-2">
                    <h2 class="heading">Price Range</h2>
                    <form method="post" class="colorlib-form-2">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="guests">Price from:</label>
                                    <div class="form-field">
                                        <i class="icon icon-arrow-down3"></i>
                                        <select name="people" id="people" class="form-control">
                                            <option value="1">1</option>
                                            <option value="1">200</option>
                                            <option value="1">300</option>
                                            <option value="1">400</option>
                                            <option value="1">1000</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="guests">Price to:</label>
                                    <div class="form-field">
                                        <i class="icon icon-arrow-down3"></i>
                                        <select name="people" id="people" class="form-control">
                                            <option value="#">2000</option>
                                            <option value="#">4000</option>
                                            <option value="#">6000</option>
                                            <option value="#">8000</option>
                                            <option value="#">10000</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}


export default Filter;