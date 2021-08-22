import { useState, useEffect } from 'react';
import { Table, TableHead, TableCell, TableRow, TableBody, Button, makeStyles } from '@material-ui/core'
import { getStocks, deleteStock } from '../Service/api';
import { Link } from 'react-router-dom';
const useStyles = makeStyles({
    table: {
        width: '90%',
        margin: '50px 0 0 50px'
    },
    thead: {
        '& > *': {
            fontSize: 20,
            background: '#AFD5EB',
            color: '#111111'
        }
    },
    row: {
        '& > *': {
            fontSize: 18
        }
    }
})


const StocksList = () => {
    const [stocks, setStocks] = useState([]);
    const classes = useStyles();
    useEffect(() => {
        getAllStocks();
    }, []);

    const deleteStockData = async (id) => {
        await deleteStock(id);
        getAllStocks();
    }

    const getAllStocks = async () => {
        let response = await getStocks();
        setStocks(response.data);
    }

    return (

        <Table className={classes.table}>
            <TableHead>
                <TableRow className={classes.thead}>
                    <TableCell>SerialNumber</TableCell>
                    <TableCell>StockId</TableCell>
                    <TableCell>StockName</TableCell>
                    <TableCell>CurrentPrice</TableCell>
                    <TableCell>LastUpdated</TableCell>
                    <TableCell>Action</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {stocks.map((stock, id) => (

                    <TableRow className={classes.row} key={stock.id}>
                        <TableCell>{++id}</TableCell>
                        <TableCell>{stock.id}</TableCell>
                        <TableCell>{stock.stockName}</TableCell>
                        <TableCell>{stock.currentPrice}</TableCell>
                        <TableCell>{stock.lastUpdate}</TableCell>
                        <TableCell>
                            <Button  variant="outlined" color="default" disableElevation style={{marginRight:10}} component={Link} to={`/edit/${stock.id}`}>Edit</Button>
                            <Button  variant="outlined" color="secondary" disableElevation onClick={() => deleteStockData(stock.id)}>Delete</Button>
                        </TableCell>
                    </TableRow>
                ))}
            </TableBody>
        </Table>
    )
}

export default StocksList;