import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';


class App extends Component
{

  constructor(props){
super(props)
this.state = { userName : "" , quotes : "" , data : [] };

this.handleChange = this.handleChange.bind(this);
this.getQuotesByUser = this.getQuotesByUser.bind(this);
this.postUserAndQuote = this.postUserAndQuote.bind(this);

  }

  handleChange(event){
    this.setState( { [event.target.name] : event.target.value }    )
  }

  getQuotesByUser(e)
  {
    e.preventDefault();
    this.setState( {  data : []  } );

    const a = axios.get("http://localhost:8300/rest/db/"+this.state.userName   )
    .then(response => {  this.setState( { data : response.data }  )       ;
                   console.log( response.data) })
    .catch(error => { console.log(error) } ) 
    
  }

  postUserAndQuote(e)
  {
    e.preventDefault();
    this.setState( {  data : [] } );
  
    let data = {
    'userName': this.state.userName ,
    'quotes' : [ this.state.quotes ]  
  }

    const a = axios.post("http://localhost:8300/rest/db/add/" , data  )
    .then(response => { console.log( response.data) })
    .catch(error => { console.log(error) } ) 
    
    
  }






  render(){

    return(
     <div>
        <label>
          Name : <input type="text" name="userName" onChange={this.handleChange} />          
        </label> 

<p></p>

         <label>
          Quotes : <input type="text" name="quotes"  onChange={this.handleChange}  />          
        </label>  
       
       <p></p>

       <button onClick={this.getQuotesByUser}>  Get Quotes </button> &nbsp;   &nbsp; &nbsp; &nbsp; 
       <button onClick={this.postUserAndQuote}>  Post Quotes </button>
 
<p></p>




<ul>
{
// this.state.data  ? 
this.state.data.map( (quotes,i) => {  
    return <li key={i}> {quotes}  </li>
  }  )
  // : this.state.data  
}
</ul>





</div>    
    )
}

}

export default App;
