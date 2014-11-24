package main.scala.service

import scala.collection._
import java.util.HashMap
import main.scala.model._
import java.util.Calendar

class WebService {
  var userList: HashMap[String, User] = new HashMap()
  var cardList: HashMap[String, HashMap[String, Card]] = new HashMap()
  var loginList: HashMap[String, HashMap[String, WebLogin]] = new HashMap()
  var baList: HashMap[String, HashMap[String, BankAccount]] = new HashMap()
  var cardMap : HashMap[String, Card] = new HashMap()
  var loginMap : HashMap[String, WebLogin] = new HashMap()
  var baMap : HashMap[String, BankAccount] = new HashMap()
  var userIndex: String = ""
  var randomNumber = new scala.util.Random()
  var userCounter = 1
  var cardCounter = 1
  var loginCounter = 1
  var baCounter = 1
  var nullUser: User = new User()
  var nullCard: Card = new Card()
  var nullCardMap: HashMap[String, Card] = new HashMap() 
  var nullWebLoginMap: HashMap[String, WebLogin] = new HashMap()
  var nullBankAccountMap: HashMap[ String, BankAccount] = new HashMap()
  
  
  //check if user exists
  def isUserExists(userId: String): Boolean = {
	  if(userList.containsKey(userId)) return true
	  return false
  }
  
  //1] create a user
  def addUser(user: User) : User = {    
	userIndex = "u-"+ userCounter
	user.setUser_id(userIndex)  
	userList.put(userIndex, user)
	userCounter += 1
	return userList.get(userIndex) 
  }
  
  //2] get a single user
  def getUser(userId: String): User = {
	  if( userList.containsKey(userId) ){
		  return userList.get(userId)
	  }
      return nullUser
  }
  
  //3] Update a user
  def updateUser(userId: String, user: User): User = {
    if( userList.containsKey(userId) ){
    	var savedUser = userList.get(userId)
        var savedCreatedAt = savedUser.getCreated_at
        savedUser.setEmail(user.email)
        savedUser.setName(user.name)
        savedUser.setPassword(user.password)
        savedUser.setCreated_at(savedCreatedAt)
        savedUser.setUpdated_at(Calendar.getInstance().getTime().toString())
    	userList.put(userId, savedUser)
	    return userList.get(userId)
    }else{
        return nullUser
    }
        
  }
  
  //4] Add a card
  def addCard(userId: String, card: Card): Card = {
	   if( userList.containsKey(userId) ){
		   var cardId: String = "c-" + cardCounter
		   card.setCard_id(cardId)
		   cardMap.put( cardId, card )
		   cardList.put(userId, cardMap)
		   cardCounter += 1
		   return cardList.get(userId).get(cardId)
	   }else{
		   return nullCard
	   }
  }
  
  //5] get all cards
  def getAllCard(userId: String): HashMap[String, Card] ={
      if( cardList.containsKey(userId) ){
    	  return cardList.get(userId)
      }
      return nullCardMap
  }
  
  //6] delete a cards
  def deleteCard(userId: String, cardId: String): Boolean = {
    if( cardList.containsKey(userId) ){
       var cardMap = cardList.get(userId)
       if( cardMap.containsKey(cardId) ){
    	  cardMap.remove(cardId)
	      cardList.put(userId,cardMap)
	      return true
       }
    }
    return false
  }
  
  //create a web login
  def addWebLogin(userId: String, webLogin: WebLogin): WebLogin = {
    var loginId = "l-" + loginCounter
    webLogin.setLogin_id(loginId) 
    loginMap.put(loginId, webLogin)
    loginList.put(userId, loginMap)
    loginCounter += 1
    return loginList.get(userId).get(loginId)
  }
  
   //get all web logins
  def getAllWebLogin(userId: String): HashMap[String, WebLogin] ={
    if( loginList.containsKey(userId) ){
    	return loginList.get(userId)
    }
      return nullWebLoginMap
  }
  
   //delete a web login
  def deleteWebLogin(userId: String, loginId: String): Boolean = {
      if( loginList.containsKey(userId)){
    	  var loginMap = loginList.get(userId)
    	  if( loginMap.containsKey(loginId) ){
    		  loginMap.remove(loginId)
    		  loginList.put(userId, loginMap)
    		  return true
    	  }
      }
      return false
  }
  
  //create a Bank Account
  def addBankAccount(userId: String, bankAccount: BankAccount): BankAccount = {
		var bankId = "b-" + baCounter
		bankAccount.setBa_id(bankId)
		baMap.put(bankId, bankAccount)
		baList.put(userId, baMap)
		baCounter += 1
		return baList.get(userId).get(bankId)
  }
  
  //get all Bank Accounts
  def getAllBankAccount(userId: String): HashMap[String, BankAccount] ={
    if( baList.containsKey(userId) ){
        return baList.get(userId)
    }
      	return nullBankAccountMap
  }
  
  //delete a bank account
  def deleteBankAccount(userId: String, baId: String): Boolean = {
      var bankMap = baList.get(userId)
      if( bankMap.containsKey(baId) ){
    	  bankMap.remove(baId)
    	  baList.put(userId,bankMap)
    	  return true
      }
      return false
  }
  
}
