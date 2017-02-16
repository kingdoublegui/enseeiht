/* $*************** KCG Version 6.1.3 (build i6) ****************
** Command: s2c613 -config H:/enseeiht/2IMA/SystemesTempsReel/tp3/Simulation\kcg_s2c_config.txt
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */
#ifndef _counter2_H_
#define _counter2_H_

#include "kcg_types.h"
#include "counter.h"
#include "decounter.h"

/* =====================  no input structure  ====================== */

/* ========================  context type  ========================= */
typedef struct {
  /* ---------------------------  outputs  --------------------------- */
  kcg_int /* counter2::n */ n;
  /* -----------------------  no local probes  ----------------------- */
  /* -----------------  no initialization variables  ----------------- */
  /* -----------------------  no local memory  ----------------------- */
  /* ---------------------  sub nodes' contexts  --------------------- */
  outC_counter /* 1 */ _1_Context_1;
  outC_decounter /* 1 */ Context_1;
  /* ----------------- no clocks of observable data ------------------ */
  /* -------------------- (-debug) no assertions  -------------------- */
  /* ------------------- (-debug) local variables -------------------- */
  kcg_int /* counter2::_L5 */ _L5;
  kcg_bool /* counter2::_L9 */ _L9;
  kcg_int /* counter2::_L10 */ _L10;
  kcg_int /* counter2::_L11 */ _L11;
  kcg_bool /* counter2::_L12 */ _L12;
  kcg_bool /* counter2::_L13 */ _L13;
} outC_counter2;

/* ===========  node initialization and cycle functions  =========== */
/* counter2 */
extern void counter2(
  /* counter2::reset */kcg_bool reset,
  /* counter2::c */kcg_bool c,
  outC_counter2 *outC);

extern void counter2_reset(outC_counter2 *outC);

#endif /* _counter2_H_ */
/* $*************** KCG Version 6.1.3 (build i6) ****************
** counter2.h
** Generation date: 2017-02-15T09:25:00
*************************************************************$ */

