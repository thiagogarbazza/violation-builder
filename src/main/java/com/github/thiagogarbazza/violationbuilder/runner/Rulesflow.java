package com.github.thiagogarbazza.violationbuilder.runner;

/**
 * The flow control of execution of rules.
 *
 * @since 1.1.0
 */
public enum Rulesflow {

  /**
   * When a rule is violated, the flow of execution of rules must continue.
   * <p>
   * <b>Example:</b><br>
   * &emsp;ValidationRule rule02 = (violationBuilder, somebOject) -&gt; { ...; return CONTINUE;};<br>
   * &emsp;RulesExecutor.rulesExecutor(asList(rule01, rule02, rule03), somebOject);<br>
   * &emsp;When executing all rules, even if rule02 has a violation, <i>rule03 will be executed</i>.
   */
  CONTINUE,

  /**
   * When a rule is violated, the flow of execution of rules must stop.
   * <p>
   * <b>Example:</b><br>
   * &emsp;ValidationRule rule02 = (violationBuilder, somebOject) -&gt; { ...; return STOP;};<br>
   * &emsp;RulesExecutor.rulesExecutor(asList(rule01, rule02, rule03), somebOject);<br>
   * &emsp;When executing all rules, even if rule02 has a violation, <i>rule03 will not be executed</i>.
   */
  STOP
}
