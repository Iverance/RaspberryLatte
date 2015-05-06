// Create DRiver Model
var mongoose = require('mongoose');

// Define our location schema
var DriverSchema = new mongoose.Schema({
  username: String,
  passengerList: [],
  passengerPickedUp:[],
  passengerDroppedOff:[],  
});

// We bind the Beer model to the BeerSchema
module.exports = mongoose.model('Driver', DriverSchema);
console.log("Driver Schema initailized");

