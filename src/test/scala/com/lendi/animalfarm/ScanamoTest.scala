package com.lendi.animalfarm

import org.scalatest._
import com.gu.scanamo._
import com.gu.scanamo.syntax._
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType._

class ScanamoTest extends FlatSpec with Matchers with BeforeAndAfterEach with EitherValues with OptionValues {

  override def beforeEach(): Unit = {
    val client = LocalDynamoDB.client()
    LocalDynamoDB.createTable(client)("farmer")('name -> S)
  }

  override def afterEach() = {
    val client = LocalDynamoDB.client()
    client.deleteTable("farmer")
  }

  it should "write a record to DynamoDB" in {
    val client = LocalDynamoDB.client()
    val table = Table[Farmer]("farmer")
    val farmer = Farmer("McDonald", 156L, Farm(List("sheep", "cow")))

    Scanamo.exec(client)(table.put(farmer))
    val result = Scanamo.exec(client)(table.get('name -> "McDonald"))
    result.isDefined shouldBe true
    result.value.right.value should equal(farmer)
  }
}
