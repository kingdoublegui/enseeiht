/* $*************** KCG Version 6.1.3 (build i6) ****************
** Command: s2c613 -config H:/enseeiht/2IMA/SystemesTempsReel/tp3/Simulation\kcg_s2c_config.txt
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */

#include "kcg_consts.h"
#include "kcg_sensors.h"
#include "decounter.h"

void decounter_reset(outC_decounter *outC)
{
  outC->init = kcg_true;
}

/* decounter */
void decounter(/* decounter::reset */kcg_bool reset, outC_decounter *outC)
{
  if (outC->init) {
    outC->_L10 = 1;
  }
  else {
    outC->_L10 = outC->_L6;
  }
  outC->_L5 = 1;
  outC->_L9 = outC->_L10 - outC->_L5;
  outC->_L8 = 0;
  outC->_L1 = 0;
  outC->_L4 = reset;
  if (outC->_L4) {
    outC->_L2 = outC->_L8;
  }
  else {
    outC->_L2 = outC->_L9;
  }
  if (outC->init) {
    outC->_L6 = outC->_L1;
  }
  else {
    outC->_L6 = outC->_L2;
  }
  outC->n = outC->_L6;
  outC->init = kcg_false;
}

/* $*************** KCG Version 6.1.3 (build i6) ****************
** decounter.c
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */

