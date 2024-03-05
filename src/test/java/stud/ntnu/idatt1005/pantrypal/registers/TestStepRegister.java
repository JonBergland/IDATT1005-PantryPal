package stud.ntnu.idatt1005.pantrypal.registers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestStepRegister {
  private static StepRegister steps;

  @BeforeAll
  static void setUp() {
    steps = new StepRegister();
    steps.addStep("Step 1");
    steps.addStep("Step 2");
    steps.addStep("Step 3");
  }

  @Test
  @DisplayName("Test getSteps")
  void testGetSteps() {
    assertEquals(steps.getSteps().get(0), "Step 1");
    assertEquals(steps.getSteps().get(1), "Step 2");
    assertEquals(steps.getSteps().get(2), "Step 3");
  }

  @Test
  @DisplayName("Test addStep")
  void testAddStep() {
    steps.addStep("Step 4");
    assertEquals(steps.getSteps().get(3), "Step 4");
  }

  @Test
  @DisplayName("Test addStep with empty string")
  void testAddStepEmptyString() {
    try {
      steps.addStep("");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Step cannot be empty.");
    }
  }

  @Test
  @DisplayName("Test addStep with null")
  void testAddStepNull() {
    try {
      steps.addStep(null);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Step cannot be empty.");
    }
  }

  @Test
  @DisplayName("Test removeStep with larger index")
  void testRemoveStepTooLargeIndex() {
    try {
      steps.removeStep(4);
    } catch (IndexOutOfBoundsException e) {
      assertEquals(e.getMessage(), "Index out of bounds must be between 0 and 2.");
    }
  }

  @Test
  @DisplayName("Test removeStep with negative index")
  void testRemoveStepNegativeIndex() {
    try {
      steps.removeStep(-1);
    } catch (IndexOutOfBoundsException e) {
      assertEquals(e.getMessage(), "Index out of bounds must be between 0 and 3.");
    }
  }

  @Test
  @DisplayName("Test removeStep with valid index")
  void testRemoveStepValidIndex() {
    steps.removeStep(0);
    assertEquals(steps.getSteps().get(0), "Step 2");
  }

}
