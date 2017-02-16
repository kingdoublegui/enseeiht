/* $*************** KCG Version 6.1.3 (build i6) ****************
** Command: s2c613 -config H:/enseeiht/2IMA/SystemesTempsReel/tp3/Simulation\kcg_s2c_config.txt
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */
#ifndef _counter_H_
#define _counter_H_

#include "kcg_types.h"

/* =====================  no input structure  ====================== */

/* ========================  context type  ========================= */
typedef struct {
  /* ---------------------------  outputs  --------------------------- */
  kcg_int /* counter::n */ n;
  /* -----------------------  no local probes  ----------------------- */
  /* -------------------- initialization variables  ------------------ */
  kcg_bool init;
  /* ----------------------- local memories  ------------------------- */
  kcg_int /* counter::_L1 */ _L1;
  /* -------------------- no sub nodes' contexts  -------------------- */
  /* ----------------- no clocks of observable data ------------------ */
  /* -------------------- (-debug) no assertions  -------------------- */
  /* ------------------- (-debug) local variables -------------------- */
  kcg_int /* counter::_L2 */ _L2;
  kcg_bool /* counter::_L3 */ _L3;
  kcg_int /* counter::_L4 */ _L4;
  kcg_int /* counter::_L6 */ _L6;
  kcg_int /* counter::_L7 */ _L7;
  kcg_int /* counter::_L8 */ _L8;
  kcg_int /* counter::_L9 */ _L9;
} outC_counter;

/* ===========  node initialization and cycle functions  =========== */
/* counter */
extern void counter(/* counter::reset */kcg_bool reset, outC_counter *outC);

extern void counter_reset(outC_counter *outC);

#endif /* _counter_H_ */
/* $*************** KCG Version 6.1.3 (build i6) ****************
** counter.h
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */

