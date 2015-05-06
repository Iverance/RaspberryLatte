// Create DRiver Model
var mongoose = require('mongoose');

// Define our location schema
var BookedSchema = new mongoose.Schema({
  location_lat: String,
  location_long: String,
  destination_lat: String,
  destination_long: String,
  numPeople: Number,
  rideWithRandoms: String,  
  username: String,  
});

module.exports = mongoose.model('Booked', BookedSchema);
console.log("Booked Schema initailized");

