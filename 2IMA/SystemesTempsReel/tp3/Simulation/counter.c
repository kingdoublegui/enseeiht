/* $*************** KCG Version 6.1.3 (build i6) ****************
** Command: s2c613 -config H:/enseeiht/2IMA/SystemesTempsReel/tp3/Simulation\kcg_s2c_config.txt
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */

#include "kcg_consts.h"
#include "kcg_sensors.h"
#include "counter.h"

void counter_reset(outC_counter *outC)
{
  outC->init = kcg_true;
}

/* counter */
void counter(/* counter::reset */kcg_bool reset, outC_counter *outC)
{
  if (outC->init) {
    outC->_L9 = - 1;
  }
  else {
    outC->_L9 = outC->_L1;
  }
  outC->_L8 = 0;
  outC->_L7 = 1;
  outC->_L6 = 0;
  outC->_L4 = outC->_L7 + outC->_L9;
  outC->_L3 = reset;
  if (outC->_L3) {
    outC->_L2 = outC->_L8;
  }
  else {
    outC->_L2 = outC->_L4;
  }
  if (outC->init) {
    outC->_L1 = outC->_L6;
  }
  else {
    outC->_L1 = outC->_L2;
  }
  outC->n = outC->_L1;
  outC->init = kcg_false;
}

/* $*************** KCG Version 6.1.3 (build i6) ****************
** counter.c
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */

