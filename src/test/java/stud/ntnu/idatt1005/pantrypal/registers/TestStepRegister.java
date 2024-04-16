package stud.ntnu.idatt1005.pantrypal.registers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    assertEquals("Step 1", steps.getSteps().get(0));
    assertEquals("Step 2", steps.getSteps().get(1));
    assertEquals("Step 3", steps.getSteps().get(2));
  }

  @Test
  @DisplayName("Test addStep")
  void testAddStep() {
    steps.addStep("Step 4");
    assertEquals("Step 4", steps.getSteps().get(3));
  }

  @Test
  @DisplayName("Test addStep with empty string")
  void testAddStepEmptyString() {
    assertThrows(IllegalArgumentException.class, () -> steps.addStep(""));
  }

  @Test
  @DisplayName("Test addStep with null")
  void testAddStepNull() {
    assertThrows(IllegalArgumentException.class, () -> steps.addStep(null));
  }

  @Test
  @DisplayName("Test removeStep with larger index")
  void testRemoveStepTooLargeIndex() {
    assertThrows(IndexOutOfBoundsException.class, () -> steps.removeStep(4));
  }

  @Test
  @DisplayName("Test removeStep with negative index")
  void testRemoveStepNegativeIndex() {
    assertThrows(IndexOutOfBoundsException.class, () -> steps.removeStep(-1));
  }

  @Test
  @DisplayName("Test removeStep with valid index")
  void testRemoveStepValidIndex() {
    steps.removeStep(0);
    assertEquals("Step 2", steps.getSteps().getFirst());
  }

  @Test
  @DisplayName("Test removeStep with valid step")
  void testRemoveStepValidStep() {
    steps.removeStep("Step 1");
    assertEquals("Step 2", steps.getSteps().getFirst());
  }

  @Test
  @DisplayName("Test removeStep with empty string")
  void testRemoveStepEmptyString() {
    assertAll(
      () -> assertThrows(IllegalArgumentException.class, () -> steps.removeStep("")),
      () -> assertThrows(IllegalArgumentException.class, () -> steps.removeStep(null)),
      () -> assertEquals(3, steps.getSteps().size())
    );
  }
}
