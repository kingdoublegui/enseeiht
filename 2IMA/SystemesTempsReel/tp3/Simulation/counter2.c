/* $*************** KCG Version 6.1.3 (build i6) ****************
** Command: s2c613 -config H:/enseeiht/2IMA/SystemesTempsReel/tp3/Simulation\kcg_s2c_config.txt
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */

#include "kcg_consts.h"
#include "kcg_sensors.h"
#include "counter2.h"

void counter2_reset(outC_counter2 *outC)
{
  /* 1 */ counter_reset(&outC->_1_Context_1);
  /* 1 */ decounter_reset(&outC->Context_1);
}

/* counter2 */
void counter2(
  /* counter2::reset */kcg_bool reset,
  /* counter2::c */kcg_bool c,
  outC_counter2 *outC)
{
  outC->_L13 = reset;
  outC->_L12 = reset;
  /* 1 */ decounter(outC->_L13, &outC->Context_1);
  outC->_L11 = outC->Context_1.n;
  /* 1 */ counter(outC->_L12, &outC->_1_Context_1);
  outC->_L10 = outC->_1_Context_1.n;
  outC->_L9 = c;
  if (outC->_L9) {
    outC->_L5 = outC->_L10;
  }
  else {
    outC->_L5 = outC->_L11;
  }
  outC->n = outC->_L5;
}

/* $*************** KCG Version 6.1.3 (build i6) ****************
** counter2.c
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */

