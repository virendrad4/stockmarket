import { AppBar, Toolbar, makeStyles } from '@material-ui/core';
import { NavLink } from 'react-router-dom';
import StockMarket from '../Assets/Images/StockMarket.jpg';

const useStyle = makeStyles({
    header: {
        background: '#111111'

    },
    tabs: {
        color: '#111111',
        marginRight: 20,
        textDecoration: 'none',
        fontSize: 20,
    },
    paperContainer: {
            backgroundImage: `url(${StockMarket})`
        }
})

const NavBar = () => {
    const classes = useStyle();
    return (
        <AppBar position="static" className={classes.paperContainer}>
            <Toolbar>
                <NavLink activeClassName="activeLink" className={classes.tabs} to="/" exact>StocksList</NavLink>
                <NavLink activeClassName="activeLink" className={classes.tabs} to="/add" exact>AddStock</NavLink>
            </Toolbar>
        </AppBar>
    )
}

export default NavBar;