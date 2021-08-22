import { useState, useEffect } from 'react';
import { FormGroup, FormControl, InputLabel, Input, Button, makeStyles, Typography } from '@material-ui/core';
import { useHistory, useParams } from 'react-router-dom';
import { editStock, getStock } from '../Service/api';

const initialValue = {
    stockName: '',
    currentPrice: ''
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

const EditStock = () => {
    const [stock, setStock] = useState(initialValue);
    const { stockName, currentPrice } = stock;
    const { id } = useParams();
    const classes = useStyles();
    let history = useHistory();

    useEffect(() => {
        loadStockDetails();
    }, []);

    const loadStockDetails = async() => {
        const response = await getStock(id);
        setStock(response.data);
    }

    const editStockDetails = async() => {
        const response = await editStock(id, stock);
        history.push('/');
    }

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

    return (
        <FormGroup className={classes.container}>
            <Typography variant="h4">Edit Information</Typography>
            <FormControl>
                <InputLabel htmlFor="my-input">StockName</InputLabel>
                <Input onChange={(e) => onValueChange(e)} name='stockName' value={stockName} id="my-input" aria-describedby="my-helper-text" />
            </FormControl>
            <FormControl>
                <InputLabel htmlFor="my-input">CurrentPrice</InputLabel>
                <Input onChange={(e) => onValueIntegerChange(e)} name='currentPrice' value={currentPrice} id="my-input" aria-describedby="my-helper-text" />
            </FormControl>
            <FormControl>
                <Button variant="contained" color="primary" onClick={() => editStockDetails()}>Edit Stock</Button>
            </FormControl>
        </FormGroup>
    )
}

export default EditStock;