import { useState } from 'react';
import { FormGroup, FormControl, InputLabel, Input, Button, makeStyles, Typography } from '@material-ui/core';
import { addStock } from '../Service/api';
import { useHistory } from 'react-router-dom';

const initialValue = {
    stockName: '',
    currentPrice: '',
}

const useStyles = makeStyles({
    container: {
        width: '50%',
        margin: '5% 0 0 25%',
        '& > *': {
            marginTop: 20
        }
    }
})

const AddStock = () => {
    const [stock, setStock] = useState(initialValue);
    const { stockName, currentPrice } = stock;
    const classes = useStyles();
    let history = useHistory();

    const onValueChange = (e) => {
        const re = /^[a-zA-Z]+$/;
        if (e.target.value === '' || re.test(e.target.value))
            setStock({...stock, [e.target.name]:e.target.value})
    }
    const onValueIntegerChange = (e) => {
        const re = /^[0-9\b]+$/;
        if (e.target.value === '' || re.test(e.target.value))
            setStock({...stock, [e.target.name]:e.target.value})

    }

    const addStockDetails = async() => {
        await addStock(stock);
        history.push('./');
    }

    return (
        <FormGroup className={classes.container}>
            <Typography variant="h4">Add Stock</Typography>
            <FormControl>
                <InputLabel htmlFor="my-input">StockName*</InputLabel>
                <Input  required onChange={(e) => onValueChange(e)} name='stockName' value={stockName} id="my-input" />
            </FormControl>
            <FormControl>
                <InputLabel htmlFor="my-input">CurrentPrice*</InputLabel>
                <Input required onChange={(e) => onValueIntegerChange(e)} name='currentPrice' value={currentPrice} id="my-input" />
            </FormControl>
            <FormControl>
                <Button variant="contained" color="secondary" onClick={() => addStockDetails()}>Add Stock</Button>
            </FormControl>
        </FormGroup>
    )
}

export default AddStock;