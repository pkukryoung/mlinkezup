package com.kdj.mlink.ezup.concurrency.rules;

/*
 * "The Java Developer's Guide to Eclipse"
 *   by D'Anjou, Fairbrother, Kehn, Kellerman, McCarthy
 * 
 * (C) Copyright International Business Machines Corporation, 2003, 2004. 
 * All Rights Reserved.
 * 
 * Code or samples provided herein are provided without warranty of any kind.
 */

import org.eclipse.core.runtime.jobs.ISchedulingRule;

import com.kdj.mlink.ezup.concurrency.TraceUtility;

/**
 * This rule contains itself and does not conflict with any other rule. 
 */
public class TypeRule implements ISchedulingRule {

   /**
    *  
    */
   public TypeRule() {
      super();
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.core.runtime.jobs.ISchedulingRule#contains(org.eclipse.core.runtime.jobs.ISchedulingRule)
    */
   public boolean contains(ISchedulingRule rule) {
      TraceUtility.traceMsg(TraceUtility.RULE, "in " + this + "contains", " sent " + rule);
      if (this == rule)
         return true;
      
      if (rule instanceof TypeRule)
         return true;
      else
         return false;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.core.runtime.jobs.ISchedulingRule#isConflicting(org.eclipse.core.runtime.jobs.ISchedulingRule)
    */
   public boolean isConflicting(ISchedulingRule rule) {
      TraceUtility.traceMsg(TraceUtility.RULE, "in " + this + "isConflicting", " sent " + rule);
      // JDG2E: 4A_ Type rule conflicts with nothing; if it is running others can start.
      // FIXED conflicts with nothing but itself, but it must at least conflict with itself
      if (rule == this)
          return true;
      return false;
   }

}