/* $*************** KCG Version 6.1.3 (build i6) ****************
** Command: s2c613 -config H:/enseeiht/2IMA/SystemesTempsReel/tp3/Simulation\kcg_s2c_config.txt
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */

#include "kcg_consts.h"
#include "kcg_sensors.h"
#include "counter3.h"

void counter3_reset(outC_counter3 *outC)
{
  outC->init = kcg_true;
  /* 2 */ counter2_reset(&outC->_1_Context_2);
  /* 2 */ counter_reset(&outC->Context_2);
}

/* counter3 */
void counter3(inC_counter3 *inC, outC_counter3 *outC)
{
  kcg_int tmp1;
  kcg_int tmp;
  kcg_int tmp3;
  kcg_int tmp2;
  
  outC->_L20 = inC->c;
  outC->_L16 = inC->c;
  outC->tmp = outC->_L16;
  outC->_L10 = inC->reset;
  if (outC->tmp) {
    /* 2 */ counter(outC->_L10, &outC->Context_2);
    tmp3 = outC->Context_2.n;
    outC->_L15 = tmp3;
  }
  else {
    if (outC->init) {
      tmp1 = 0;
    }
    else {
      tmp1 = outC->_L15;
    }
    outC->_L15 = tmp1;
  }
  outC->_L17 = inC->c;
  outC->_L18 = !outC->_L17;
  outC->tmp2 = outC->_L18;
  outC->_L11 = inC->reset;
  outC->_L13 = inC->c;
  if (outC->tmp2) {
    /* 2 */ counter2(outC->_L11, outC->_L13, &outC->_1_Context_2);
    tmp2 = outC->_1_Context_2.n;
    outC->_L14 = tmp2;
  }
  else {
    if (outC->init) {
      tmp = 0;
    }
    else {
      tmp = outC->_L14;
    }
    outC->_L14 = tmp;
  }
  if (outC->_L20) {
    outC->_L19 = outC->_L15;
  }
  else {
    outC->_L19 = outC->_L14;
  }
  outC->n = outC->_L19;
  outC->init = kcg_false;
}

/* $*************** KCG Version 6.1.3 (build i6) ****************
** counter3.c
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */

