// Will Tobey
// wtobey1@jhu.edu

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class SparseGraphTest extends GraphTestBase {

   @Before
   public void newGraphs() {

      graph1 = new SparseGraph<Integer, Integer>();
      graph2 = new SparseGraph<Integer, Integer>();

   }
}