import React, {Component} from 'react';
import SearchBar from "../SearchBar";
import Cards from "../Cards";

class Home extends Component {
    render() {
        return (
            <>
                <SearchBar />
                <Cards />
            </>
        )
    }
}

export default Home