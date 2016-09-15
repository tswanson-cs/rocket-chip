// See LICENSE for license details.

package unittest

import Chisel._
import cde.Parameters
import rocketchip._

class TestHarness(implicit val p: Parameters) extends Module {
  val io = new Bundle {
    val success = Bool(OUTPUT)
  }

  p(NCoreplexExtClients).assign(0)
  p(ConfigString).assign("")

  val l1params = p.alterPartial({ case uncore.tilelink.TLId => "L1toL2" })
  val tests = Module(new UnitTestSuite()(l1params))

  io.success := tests.io.finished
}
